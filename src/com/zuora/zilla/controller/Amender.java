package com.zuora.zilla.controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import com.zuora.api.*;
import com.zuora.api.object.*;

import com.zuora.zilla.model.*;
import com.zuora.zilla.util.*;

/**
 * The Amender class handles amendments and amendment previews for a user's active subscription.
 * 
 * @author Eric Neto <eric.neto@zuora.com>
 */
public class Amender {
	/** The Zuora API instance used to handle soap calls. */
	private ZApi zapi;
	
	public Amender() throws Exception {
		// get the stub and the helper
		try {
			zapi = new ZApi();
		} catch (Exception e) {
			throw new Exception("INVALID_LOGIN");
		}
	}
	
	/**
	 * \brief Adds a new ratePlan to the current user's subscription. 
	 * 
	 * New products added to the user's subscription are effective immediately. 
	 * A quantity can also be supplied, that will apply to all recurring and one-time charges on the rate plan that do not use flat fee pricing.
	 * @param $accountName Name of the target account
	 * @param $prpId ID of the Rate Plan to be added.
	 * @param $qty Amount of UOM for the RatePlan being added. A null value can be passed for product rate plans that use flat fee pricing
	 * @param $preview Flag to determine whether this function will be used to create an amendment, or preview an invoice
	 * @return Amend Result
	 */
	public AmenderResult addRatePlan(String accountName, String prpId, BigDecimal qty, boolean preview) {

		SubscriptionManager subManager = null;
		try {
			subManager = new SubscriptionManager();
		} catch (Exception e) {
			e.printStackTrace();
		}
		AmenderSubscription subscription = subManager.getCurrentSubscription(accountName);
		
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
		Calendar today = Calendar.getInstance();
		
		Amendment amendment = new Amendment();
		
		amendment.setName("New Product");
		amendment.setStatus("Completed");
		amendment.setType("NewProduct");
		String subscriptionId = subscription.getSubscriptionId();
		amendment.setSubscriptionId(subscriptionId);
		amendment.setContractEffectiveDate(today);
		amendment.setServiceActivationDate(today);
		amendment.setCustomerAcceptanceDate(today);
		amendment.setEffectiveDate(today);
		
		RatePlanData rpd = new RatePlanData();
		RatePlan rp = new RatePlan();
		rp.setProductRatePlanId(prpId);
		rpd.setRatePlan(rp);
		amendment.setRatePlanData(rpd);

		// If there is price per quantity defined, set up RatePlanCharge data to override all quantities on this plan with the given amount
		if (qty != null) {
			CatalogRatePlan crp = Catalog.getRatePlan(prpId);
			if (crp.getQuantifiable()){
				ArrayList<RatePlanChargeData> rpcds = new ArrayList<RatePlanChargeData>();
				ArrayList<CatalogCharge> ccharges = crp.getCharges();
				for(CatalogCharge ccharge : ccharges){
					if((ccharge.getChargeModel().equals("Per Unit Pricing") || ccharge.getChargeModel().equals("Tiered Pricing") || ccharge.getChargeModel().equals("Volume Pricing")) &&
						!ccharge.getChargeType().equals("Usage"))
					{
						RatePlanChargeData rpcd = new RatePlanChargeData();
						RatePlanCharge rpc = new RatePlanCharge();
						rpc.setProductRatePlanChargeId(ccharge.getId());
						rpc.setQuantity(qty);
						rpcd.setRatePlanCharge(rpc);
						rpcds.add(rpcd);
					}
				}
				rpcds.trimToSize();
				if(rpcds.size()>0){
					RatePlanChargeData[] rpcdsA = rpcds.toArray(new RatePlanChargeData[rpcds.size()]);
					rpd.setRatePlanChargeData(rpcdsA);
				}
			}
		}
		
		AmendOptions ao = new AmendOptions();
		ao.setGenerateInvoice(true);
		ao.setProcessPayments(false);

		PreviewOptions po = new PreviewOptions();
		po.setEnablePreviewMode(preview);
		po.setNumberOfPeriods(1);

		AmendRequest amReq = new AmendRequest();

		amReq.setAmendments(new Amendment[]{ amendment });
		amReq.setAmendOptions(ao);
		amReq.setPreviewOptions(po);

		AmendResult[] amResp = null;
		AmenderResult amenderRes = new AmenderResult();
		try{
			amResp = zapi.zAmend(new AmendRequest[] {amReq});
			amenderRes.setSuccess(amResp[0].getSuccess());
			if(!amenderRes.isSuccess()){
				amenderRes.setError(amResp[0].getErrors()[0].getMessage());
			} else {
				if(amResp[0].getInvoiceDatas()!=null && amResp[0].getInvoiceDatas()[0].getInvoice()!=null){
					//TODO: Get amount without tax if tax is enabled; Amount if tax is disabled.
					amenderRes.setInvoiceAmount((amResp[0].getInvoiceDatas()[0].getInvoice().getAmountWithoutTax()).doubleValue());
				}
			}
		} catch (Exception e){
			Logger.Log(e.getMessage());
			amenderRes.setSuccess(false);
			amenderRes.setError(e.getMessage());
		}
		
		return amenderRes;
	}

	/**
	 * \brief Removes a ratePlan from the current user's subscription. 
	 * 
	 * New products added to the user's subscription are effective immediately. 
	 * A quantity can also be supplied, that will apply to all recurring and one-time charges on the rate plan that do not use flat fee pricing.
	 * @param $accountName Name of the target account
	 * @param $rpId ID of the rate plan to be added.
	 * @param $qty Amount of UOM for the RatePlan being added. A null value can be passed for product rate plans that use flat fee pricing
	 * @param $preview Flag to determine whether this function will be used to create an amendment, or preview an invoice
	 * @return Amend Result
	 */
	public AmenderResult removeRatePlan(String accountName, String rpId, boolean preview) {
	
		AmenderResult amRes = new AmenderResult();
		SubscriptionManager subManager = null;
		try {
			subManager = new SubscriptionManager();
		} catch (Exception e) {
			e.printStackTrace();
		}
		AmenderSubscription subscription = subManager.getCurrentSubscription(accountName);
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
		Calendar endOfCycle = subscription.getEndOfTermDate();
		
		if(preview){
			amRes.setEffectiveDate(endOfCycle);
			amRes.setSuccess(true);
			return amRes;
		}
		
		Amendment amendment = new Amendment();
		
		amendment.setName("Remove Product");
		amendment.setStatus("Completed");
		amendment.setType("RemoveProduct");
		String subscriptionId = subscription.getSubscriptionId();
		amendment.setSubscriptionId(subscriptionId);
		amendment.setContractEffectiveDate(endOfCycle);
		amendment.setServiceActivationDate(endOfCycle);
		amendment.setCustomerAcceptanceDate(endOfCycle);
		amendment.setEffectiveDate(endOfCycle);
		
		RatePlanData rpd = new RatePlanData();
		RatePlan rp = new RatePlan();
		rp.setAmendmentSubscriptionRatePlanId(rpId);
		rpd.setRatePlan(rp);
		amendment.setRatePlanData(rpd);
		
		AmendOptions ao = new AmendOptions();
		ao.setGenerateInvoice(true);
		ao.setProcessPayments(false);
		
		PreviewOptions po = new PreviewOptions();
		po.setEnablePreviewMode(preview);
		po.setNumberOfPeriods(1);
		
		AmendRequest amReq = new AmendRequest();
		
		amReq.setAmendments(new Amendment[] { amendment });
		amReq.setAmendOptions(ao);
		amReq.setPreviewOptions(po);
		
		AmendResult[] amResp = null;
		AmenderResult amenderRes = new AmenderResult();
		try{
			amResp = zapi.zAmend(new AmendRequest[] {amReq});
			amenderRes.setSuccess(amResp[0].getSuccess());
			if(!amenderRes.isSuccess()){
				amenderRes.setError(amResp[0].getErrors()[0].getMessage());
			} else {
				if(amResp[0].getInvoiceDatas()!=null && amResp[0].getInvoiceDatas()[0].getInvoice()!=null){
					//Get amount without tax if tax is enabled; Amount if tax is disabled.
					try{
						amenderRes.setInvoiceAmount((amResp[0].getInvoiceDatas()[0].getInvoice().getAmountWithoutTax()).doubleValue());
					} catch (Exception e){
						amenderRes.setInvoiceAmount((amResp[0].getInvoiceDatas()[0].getInvoice().getAmount()).doubleValue());
					}
				}
			}
		} catch (Exception e){
			Logger.Log(e.getMessage());
			amenderRes.setSuccess(false);
			amenderRes.setError(e.getMessage());
		}
		
		return amenderRes;
	}
}
