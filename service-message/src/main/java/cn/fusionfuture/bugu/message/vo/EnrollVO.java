package cn.fusionfuture.bugu.message.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EnrollVO对象", description="")
public class EnrollVO implements Serializable {
    private static final long serialVersionUID = -609962923751712596L;
    private Long id;
    private Long sendUserId;
    private String sendUserName;
    private String sendAvatarUrl;
    private Long receiveUserId;
    private Integer maxEnrollQuantity;
    private Integer currentEnrollCount;
    private Long planId;
    private String planPattern;
    private String planName;
    private Integer planTypeId;
    private LocalTime enrollTime;
    private Boolean isChecked;

    public void setId(Long id) {
        this.id = id;
    }

    public void setSendUserId(Long sendUserId) {
        this.sendUserId = sendUserId;
    }

    public void setReceiveUserId(Long receiveUserId) {
        this.receiveUserId = receiveUserId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    private Boolean isHidden;


    public String getSendUserName() {
        return sendUserName;
    }

    public void setSendUserName(String sendUserName) {
        this.sendUserName = sendUserName;
    }

    public String getSendAvatarUrl() {
        return sendAvatarUrl;
    }

    public void setSendAvatarUrl(String sendAvatarUrl) {
        this.sendAvatarUrl = sendAvatarUrl;
    }


    public Integer getMaxEnrollQuantity() {
        return maxEnrollQuantity;
    }

    public void setMaxEnrollQuantity(Integer maxEnrollQuantity) {
        this.maxEnrollQuantity = maxEnrollQuantity;
    }

    public Integer getCurrentEnrollCount() {
        return currentEnrollCount;
    }

    public void setCurrentEnrollCount(Integer currentEnrollCount) {
        this.currentEnrollCount = currentEnrollCount;
    }

    public String getPlanPattern() {
        return planPattern;
    }

    public void setPlanPattern(String planPattern) {
        this.planPattern = planPattern;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public Integer getPlanTypeId() {
        return planTypeId;
    }

    public void setPlanTypeId(Integer planTypeId) {
        this.planTypeId = planTypeId;
    }

    public LocalTime getEnrollTime() {
        return enrollTime;
    }

    public void setEnrollTime(LocalTime enrollTime) {
        this.enrollTime = enrollTime;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

    public Boolean getHidden() {
        return isHidden;
    }

    public void setHidden(Boolean hidden) {
        isHidden = hidden;
    }
}

