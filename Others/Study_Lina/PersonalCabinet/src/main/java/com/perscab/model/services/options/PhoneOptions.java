package com.perscab.model.services.options;

public interface PhoneOptions {

    int getTalkLimit();

    void setTalkLimit(int talkLimit);

    int getDataLimit();

    void setDataLimit(int dataLimit);

    boolean isVoiceMail();

    void setVoiceMail(boolean voiceMail);
}
