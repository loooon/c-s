package com.credit.common.sms;


public abstract class SmsEntry implements ISmsEntry 
{
    int retryLimit = 3;
    
    public int getRetryLimit() 
    {
        return retryLimit;
    }

    public void setRetryLimit(int retryLimit) 
    {
        this.retryLimit = retryLimit;
    }

    public void decreaseRetryLimit() 
    {
        retryLimit--;
    }
    
    public void increaseRetryLimit()
    {
        retryLimit++;
    }
}