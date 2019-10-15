package com.perscab.model.services;

import com.perscab.model.services.options.PhoneOptions;
import static com.perscab.model.services.PhoneServicePlan.PhoneServiceOptionsEnum.*;

public class PhoneServicePlan extends ServicePlan implements PhoneOptions {

    public PhoneServicePlan(ServiceType type) {
        super(type);
    }

    public PhoneServicePlan(long id, String name, ServiceType type, double price) {
        super(id, name, type, price);
    }

    @Override
    public int getTalkLimit() {
        return (int) getOptions().get(TALK_LIMIT.getOption());
    }

    @Override
    public void setTalkLimit(int talkLimit) {
        getOptions().put(TALK_LIMIT.getOption(), talkLimit);
    }

    @Override
    public int getDataLimit() {
        return (int) getOptions().get(DATA_LIMIT.getOption());
    }

    @Override
    public void setDataLimit(int dataLimit) {
        getOptions().put(DATA_LIMIT.getOption(), dataLimit);
    }

    @Override
    public boolean isVoiceMail() {
        return (boolean) getOptions().get(VOICE_MAIL.getOption());
    }

    @Override
    public void setVoiceMail(boolean voiceMail) {
        getOptions().put(VOICE_MAIL.getOption(), voiceMail);
    }

    public enum PhoneServiceOptionsEnum {

//        TALK_LIMIT(new ServiceOptionKey("talkLimit")),
//        DATA_LIMIT(new ServiceOptionKey("dataLimit")),
//        VOICE_MAIL(new ServiceOptionKey("voiceMail", true));
        TALK_LIMIT(new ServiceOptionKey("db.service_opts.phone.talk_limit")),
        DATA_LIMIT(new ServiceOptionKey("db.service_opts.phone.data_limit")),
        VOICE_MAIL(new ServiceOptionKey("db.service_opts.phone.voice_mail", true));

        private ServiceOptionKey option;

        PhoneServiceOptionsEnum(ServiceOptionKey option) {
            this.option = option;
        }

        public ServiceOptionKey getOption() {
            return option;
        }
    }
}
