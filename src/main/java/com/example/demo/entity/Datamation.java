package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

//使用插件，让其与数据库的表名一一对应
@TableName("datasmation")

@Data
public class Datamation {
    //    id定义为自增,数据库主键名字如果不是id，必须加上@TableId(value="")
    @TableId(type = IdType.AUTO)
    public Integer mationid;
    public String stopid;
    public String stopname;
    public String stopaddr;
    public String stopdetail;
    public String stoptype;
    public String stoplon;
    public String stoplat;
    public String validflag;
    public String agvshift;
    public String stoptimes;
    public String stoptimee;
    public String agvid;

    public String getAgvid() {
        return agvid;
    }

    public void setAgvid(String agvid) {
        this.agvid = agvid;
    }

    public String extfields1;
    public String extfields2;
    public String extfields3;

    public Integer getMationid() {
        return mationid;
    }

    public void setMationid(Integer mationid) {
        this.mationid = mationid;
    }

    public String getStopid() {
        return stopid;
    }

    public void setStopid(String stopid) {
        this.stopid = stopid;
    }

    public String getStopname() {
        return stopname;
    }

    public void setStopname(String stopname) {
        this.stopname = stopname;
    }

    public String getStopaddr() {
        return stopaddr;
    }

    public void setStopaddr(String stopaddr) {
        this.stopaddr = stopaddr;
    }

    public String getStopdetail() {
        return stopdetail;
    }

    public void setStopdetail(String stopdetail) {
        this.stopdetail = stopdetail;
    }

    public String getStoptype() {
        return stoptype;
    }

    public void setStoptype(String stoptype) {
        this.stoptype = stoptype;
    }

    public String getStoplon() {
        return stoplon;
    }

    public void setStoplon(String stoplon) {
        this.stoplon = stoplon;
    }

    public String getStoplat() {
        return stoplat;
    }

    public void setStoplat(String stoplat) {
        this.stoplat = stoplat;
    }

    public String getValidflag() {
        return validflag;
    }

    public void setValidflag(String validflag) {
        this.validflag = validflag;
    }

    public String getAgvshift() {
        return agvshift;
    }

    public void setAgvshift(String agvshift) {
        this.agvshift = agvshift;
    }

    public String getStoptimes() {
        return stoptimes;
    }

    public void setStoptimes(String stoptimes) {
        this.stoptimes = stoptimes;
    }

    public String getStoptimee() {
        return stoptimee;
    }

    public void setStoptimee(String stoptimee) {
        this.stoptimee = stoptimee;
    }

    public String getExtfields1() {
        return extfields1;
    }

    public void setExtfields1(String extfields1) {
        this.extfields1 = extfields1;
    }

    public String getExtfields2() {
        return extfields2;
    }

    public void setExtfields2(String extfields2) {
        this.extfields2 = extfields2;
    }

    public String getExtfields3() {
        return extfields3;
    }

    public void setExtfields3(String extfields3) {
        this.extfields3 = extfields3;
    }
}
