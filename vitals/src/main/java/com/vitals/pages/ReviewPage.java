package com.vitals.pages;

public class ReviewPage extends BasePage {

    HeaderModule headerModule;
    FooterModule footerModule;

    public ReviewPage() {
        headerModule = new HeaderModule();
        footerModule = new FooterModule();
    }

    public HeaderModule headerModule(){
        return headerModule;
    }

    public FooterModule footerModule() {
        return footerModule;
    }

}
