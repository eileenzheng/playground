package com.vitals.helpers;

public interface Constants {

    static public final String SELENIUM_REMOTE = "http://thvitdatadev01.mdx.med:4444/wd/hub";
    static public final int SELENIUM_IMPLICIT_WAIT = 5;
    static public final int SELENIUM_EXPLICIT_WAIT = 5;

    static public final String SAML_URL = "http://cbc-qa.mle.mdx.med/simplesaml/module.php/core/authenticate.php?as=cbc-local";
    static public final String SAML_USER = "mleuser_2";
    static public final String SAML_PW = "mle";
    
    static public enum SearchType {NAME, LOCATION, SPECIALTY, CONDITION, UCC}
}
