package com.dlf.a8_11_work.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class NoteBean {
    @Id
    Long idl;
    String title;
    String desc;
    @Generated(hash = 1050492792)
    public NoteBean(Long idl, String title, String desc) {
        this.idl = idl;
        this.title = title;
        this.desc = desc;
    }
    @Generated(hash = 451626881)
    public NoteBean() {
    }
    public Long getIdl() {
        return this.idl;
    }
    public void setIdl(Long idl) {
        this.idl = idl;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDesc() {
        return this.desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
}
