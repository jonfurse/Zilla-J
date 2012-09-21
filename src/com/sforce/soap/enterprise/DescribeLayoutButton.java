package com.sforce.soap.enterprise;/** * Generated class, please do not edit. */public class DescribeLayoutButton implements com.sforce.ws.bind.XMLizable {  /**   * Constructor   */  public DescribeLayoutButton() {  }        /**   * element  : custom of type {http://www.w3.org/2001/XMLSchema}boolean   * java type: boolean   */  private static final com.sforce.ws.bind.TypeInfo custom__typeInfo =    new com.sforce.ws.bind.TypeInfo("urn:enterprise.soap.sforce.com","custom","http://www.w3.org/2001/XMLSchema","boolean",1,1,true);  private boolean custom__is_set = false;  private boolean custom;  public boolean getCustom() {    return custom;  }    public boolean isCustom() {    return custom;  }    public void setCustom(boolean custom) {    this.custom = custom;    custom__is_set = true;  }    /**   * element  : label of type {http://www.w3.org/2001/XMLSchema}string   * java type: java.lang.String   */  private static final com.sforce.ws.bind.TypeInfo label__typeInfo =    new com.sforce.ws.bind.TypeInfo("urn:enterprise.soap.sforce.com","label","http://www.w3.org/2001/XMLSchema","string",1,1,true);  private boolean label__is_set = false;  private java.lang.String label;  public java.lang.String getLabel() {    return label;  }    public void setLabel(java.lang.String label) {    this.label = label;    label__is_set = true;  }    /**   * element  : name of type {http://www.w3.org/2001/XMLSchema}string   * java type: java.lang.String   */  private static final com.sforce.ws.bind.TypeInfo name__typeInfo =    new com.sforce.ws.bind.TypeInfo("urn:enterprise.soap.sforce.com","name","http://www.w3.org/2001/XMLSchema","string",1,1,true);  private boolean name__is_set = false;  private java.lang.String name;  public java.lang.String getName() {    return name;  }    public void setName(java.lang.String name) {    this.name = name;    name__is_set = true;  }    /**   */  public void write(javax.xml.namespace.QName __element,      com.sforce.ws.parser.XmlOutputStream __out, com.sforce.ws.bind.TypeMapper __typeMapper)      throws java.io.IOException {    __out.writeStartTag(__element.getNamespaceURI(), __element.getLocalPart());        writeFields(__out, __typeMapper);    __out.writeEndTag(__element.getNamespaceURI(), __element.getLocalPart());  }  protected void writeFields(com.sforce.ws.parser.XmlOutputStream __out,      com.sforce.ws.bind.TypeMapper __typeMapper) throws java.io.IOException {       __typeMapper.writeBoolean(__out, custom__typeInfo, custom, custom__is_set);    __typeMapper.writeString(__out, label__typeInfo, label, label__is_set);    __typeMapper.writeString(__out, name__typeInfo, name, name__is_set);  }  public void load(com.sforce.ws.parser.XmlInputStream __in,      com.sforce.ws.bind.TypeMapper __typeMapper) throws java.io.IOException, com.sforce.ws.ConnectionException {    __typeMapper.consumeStartTag(__in);    loadFields(__in, __typeMapper);    __typeMapper.consumeEndTag(__in);  }  protected void loadFields(com.sforce.ws.parser.XmlInputStream __in,      com.sforce.ws.bind.TypeMapper __typeMapper) throws java.io.IOException, com.sforce.ws.ConnectionException {       __in.peekTag();    if (__typeMapper.verifyElement(__in, custom__typeInfo)) {      setCustom((boolean)__typeMapper.readBoolean(__in, custom__typeInfo, boolean.class));    }    __in.peekTag();    if (__typeMapper.verifyElement(__in, label__typeInfo)) {      setLabel((java.lang.String)__typeMapper.readString(__in, label__typeInfo, java.lang.String.class));    }    __in.peekTag();    if (__typeMapper.verifyElement(__in, name__typeInfo)) {      setName((java.lang.String)__typeMapper.readString(__in, name__typeInfo, java.lang.String.class));    }  }  public String toString() {    java.lang.StringBuilder sb = new java.lang.StringBuilder();    sb.append("[DescribeLayoutButton ");        sb.append(" custom=");    sb.append("'"+com.sforce.ws.util.Verbose.toString(custom)+"'\n");    sb.append(" label=");    sb.append("'"+com.sforce.ws.util.Verbose.toString(label)+"'\n");    sb.append(" name=");    sb.append("'"+com.sforce.ws.util.Verbose.toString(name)+"'\n");    sb.append("]\n");    return sb.toString();  }}