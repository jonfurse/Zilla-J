package com.sforce.soap.enterprise;/** * Generated class, please do not edit. */public class DescribeLayout implements com.sforce.ws.bind.XMLizable {  /**   * Constructor   */  public DescribeLayout() {  }        /**   * element  : buttonLayoutSection of type {urn:enterprise.soap.sforce.com}DescribeLayoutButtonSection   * java type: com.sforce.soap.enterprise.DescribeLayoutButtonSection   */  private static final com.sforce.ws.bind.TypeInfo buttonLayoutSection__typeInfo =    new com.sforce.ws.bind.TypeInfo("urn:enterprise.soap.sforce.com","buttonLayoutSection","urn:enterprise.soap.sforce.com","DescribeLayoutButtonSection",0,1,true);  private boolean buttonLayoutSection__is_set = false;  private com.sforce.soap.enterprise.DescribeLayoutButtonSection buttonLayoutSection;  public com.sforce.soap.enterprise.DescribeLayoutButtonSection getButtonLayoutSection() {    return buttonLayoutSection;  }    public void setButtonLayoutSection(com.sforce.soap.enterprise.DescribeLayoutButtonSection buttonLayoutSection) {    this.buttonLayoutSection = buttonLayoutSection;    buttonLayoutSection__is_set = true;  }    /**   * element  : detailLayoutSections of type {urn:enterprise.soap.sforce.com}DescribeLayoutSection   * java type: com.sforce.soap.enterprise.DescribeLayoutSection[]   */  private static final com.sforce.ws.bind.TypeInfo detailLayoutSections__typeInfo =    new com.sforce.ws.bind.TypeInfo("urn:enterprise.soap.sforce.com","detailLayoutSections","urn:enterprise.soap.sforce.com","DescribeLayoutSection",0,-1,true);  private boolean detailLayoutSections__is_set = false;  private com.sforce.soap.enterprise.DescribeLayoutSection[] detailLayoutSections = new com.sforce.soap.enterprise.DescribeLayoutSection[0];  public com.sforce.soap.enterprise.DescribeLayoutSection[] getDetailLayoutSections() {    return detailLayoutSections;  }    public void setDetailLayoutSections(com.sforce.soap.enterprise.DescribeLayoutSection[] detailLayoutSections) {    this.detailLayoutSections = detailLayoutSections;    detailLayoutSections__is_set = true;  }    /**   * element  : editLayoutSections of type {urn:enterprise.soap.sforce.com}DescribeLayoutSection   * java type: com.sforce.soap.enterprise.DescribeLayoutSection[]   */  private static final com.sforce.ws.bind.TypeInfo editLayoutSections__typeInfo =    new com.sforce.ws.bind.TypeInfo("urn:enterprise.soap.sforce.com","editLayoutSections","urn:enterprise.soap.sforce.com","DescribeLayoutSection",0,-1,true);  private boolean editLayoutSections__is_set = false;  private com.sforce.soap.enterprise.DescribeLayoutSection[] editLayoutSections = new com.sforce.soap.enterprise.DescribeLayoutSection[0];  public com.sforce.soap.enterprise.DescribeLayoutSection[] getEditLayoutSections() {    return editLayoutSections;  }    public void setEditLayoutSections(com.sforce.soap.enterprise.DescribeLayoutSection[] editLayoutSections) {    this.editLayoutSections = editLayoutSections;    editLayoutSections__is_set = true;  }    /**   * element  : id of type {urn:enterprise.soap.sforce.com}ID   * java type: java.lang.String   */  private static final com.sforce.ws.bind.TypeInfo id__typeInfo =    new com.sforce.ws.bind.TypeInfo("urn:enterprise.soap.sforce.com","id","urn:enterprise.soap.sforce.com","ID",1,1,true);  private boolean id__is_set = false;  private java.lang.String id;  public java.lang.String getId() {    return id;  }    public void setId(java.lang.String id) {    this.id = id;    id__is_set = true;  }    /**   * element  : relatedLists of type {urn:enterprise.soap.sforce.com}RelatedList   * java type: com.sforce.soap.enterprise.RelatedList[]   */  private static final com.sforce.ws.bind.TypeInfo relatedLists__typeInfo =    new com.sforce.ws.bind.TypeInfo("urn:enterprise.soap.sforce.com","relatedLists","urn:enterprise.soap.sforce.com","RelatedList",0,-1,true);  private boolean relatedLists__is_set = false;  private com.sforce.soap.enterprise.RelatedList[] relatedLists = new com.sforce.soap.enterprise.RelatedList[0];  public com.sforce.soap.enterprise.RelatedList[] getRelatedLists() {    return relatedLists;  }    public void setRelatedLists(com.sforce.soap.enterprise.RelatedList[] relatedLists) {    this.relatedLists = relatedLists;    relatedLists__is_set = true;  }    /**   */  public void write(javax.xml.namespace.QName __element,      com.sforce.ws.parser.XmlOutputStream __out, com.sforce.ws.bind.TypeMapper __typeMapper)      throws java.io.IOException {    __out.writeStartTag(__element.getNamespaceURI(), __element.getLocalPart());        writeFields(__out, __typeMapper);    __out.writeEndTag(__element.getNamespaceURI(), __element.getLocalPart());  }  protected void writeFields(com.sforce.ws.parser.XmlOutputStream __out,      com.sforce.ws.bind.TypeMapper __typeMapper) throws java.io.IOException {       __typeMapper.writeObject(__out, buttonLayoutSection__typeInfo, buttonLayoutSection, buttonLayoutSection__is_set);    __typeMapper.writeObject(__out, detailLayoutSections__typeInfo, detailLayoutSections, detailLayoutSections__is_set);    __typeMapper.writeObject(__out, editLayoutSections__typeInfo, editLayoutSections, editLayoutSections__is_set);    __typeMapper.writeString(__out, id__typeInfo, id, id__is_set);    __typeMapper.writeObject(__out, relatedLists__typeInfo, relatedLists, relatedLists__is_set);  }  public void load(com.sforce.ws.parser.XmlInputStream __in,      com.sforce.ws.bind.TypeMapper __typeMapper) throws java.io.IOException, com.sforce.ws.ConnectionException {    __typeMapper.consumeStartTag(__in);    loadFields(__in, __typeMapper);    __typeMapper.consumeEndTag(__in);  }  protected void loadFields(com.sforce.ws.parser.XmlInputStream __in,      com.sforce.ws.bind.TypeMapper __typeMapper) throws java.io.IOException, com.sforce.ws.ConnectionException {       __in.peekTag();    if (__typeMapper.isElement(__in, buttonLayoutSection__typeInfo)) {      setButtonLayoutSection((com.sforce.soap.enterprise.DescribeLayoutButtonSection)__typeMapper.readObject(__in, buttonLayoutSection__typeInfo, com.sforce.soap.enterprise.DescribeLayoutButtonSection.class));    }    __in.peekTag();    if (__typeMapper.isElement(__in, detailLayoutSections__typeInfo)) {      setDetailLayoutSections((com.sforce.soap.enterprise.DescribeLayoutSection[])__typeMapper.readObject(__in, detailLayoutSections__typeInfo, com.sforce.soap.enterprise.DescribeLayoutSection[].class));    }    __in.peekTag();    if (__typeMapper.isElement(__in, editLayoutSections__typeInfo)) {      setEditLayoutSections((com.sforce.soap.enterprise.DescribeLayoutSection[])__typeMapper.readObject(__in, editLayoutSections__typeInfo, com.sforce.soap.enterprise.DescribeLayoutSection[].class));    }    __in.peekTag();    if (__typeMapper.verifyElement(__in, id__typeInfo)) {      setId((java.lang.String)__typeMapper.readString(__in, id__typeInfo, java.lang.String.class));    }    __in.peekTag();    if (__typeMapper.isElement(__in, relatedLists__typeInfo)) {      setRelatedLists((com.sforce.soap.enterprise.RelatedList[])__typeMapper.readObject(__in, relatedLists__typeInfo, com.sforce.soap.enterprise.RelatedList[].class));    }  }  public String toString() {    java.lang.StringBuilder sb = new java.lang.StringBuilder();    sb.append("[DescribeLayout ");        sb.append(" buttonLayoutSection=");    sb.append("'"+com.sforce.ws.util.Verbose.toString(buttonLayoutSection)+"'\n");    sb.append(" detailLayoutSections=");    sb.append("'"+com.sforce.ws.util.Verbose.toString(detailLayoutSections)+"'\n");    sb.append(" editLayoutSections=");    sb.append("'"+com.sforce.ws.util.Verbose.toString(editLayoutSections)+"'\n");    sb.append(" id=");    sb.append("'"+com.sforce.ws.util.Verbose.toString(id)+"'\n");    sb.append(" relatedLists=");    sb.append("'"+com.sforce.ws.util.Verbose.toString(relatedLists)+"'\n");    sb.append("]\n");    return sb.toString();  }}