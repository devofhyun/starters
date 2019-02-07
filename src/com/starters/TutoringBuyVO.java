package com.starters;

// 튜터링 신청(결제) VO: 튜터링 신청(결제) 관리 시스템에 필요한 정보 저장

public class TutoringBuyVO {
   private int tutoringApplyId;
   private int tutoringId;
   private String tuteeId;
   private String tutoringName;
   private String applyDate;
   private String paymentInfo;
   private int price;
   
   public TutoringBuyVO(){}
   public TutoringBuyVO(int tutoringApplyId, int tutoringId, String tuteeId, String tutoringName,String applyDate, String paymentInfo, int price){
      super();
      this.tutoringApplyId = tutoringApplyId;
      this.tutoringId = tutoringId;
      this.tuteeId = tuteeId;
      this.tutoringName = tutoringName;
      this.applyDate = applyDate;
      this.paymentInfo = paymentInfo;
      this.price = price;
   }
   public int getTutoringApplyId() {
      return tutoringApplyId;
   }
   public void setTutoringApplyId(int tutoringApplyId) {
      this.tutoringApplyId = tutoringApplyId;
   }
   public int getTutoringId() {
      return tutoringId;
   }
   public void setTutoringId(int tutoringId) {
      this.tutoringId = tutoringId;
   }
   public String getTuteeId() {
      return tuteeId;
   }
   public void setTuteeId(String tuteeId) {
      this.tuteeId = tuteeId;
   }
   public String getTutoringName() {
      return tutoringName;
   }
   public void setTutoringName(String tutoringName) {
      this.tutoringName = tutoringName;
   }
   public String getApplyDate() {
      return applyDate;
   }
   public void setApplyDate(String applyDate) {
      this.applyDate = applyDate;
   }
   public String getPaymentInfo() {
      return paymentInfo;
   }
   public void setPaymentInfo(String paymentInfo) {
      this.paymentInfo = paymentInfo;
   }
   public int getPrice() {
      return price;
   }
   public void setPrice(int price) {
      this.price = price;
   }
   @Override
   public String toString() {
      return "TutoringBuyVO [tutoringApplyId=" + tutoringApplyId + ", tutoringId=" + tutoringId + ", tuteeId="
            + tuteeId + ", tutoringName=" + tutoringName + ", applyDate=" + applyDate + ", paymentInfo="
            + paymentInfo + ", price=" + price + "]";
   }
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((applyDate == null) ? 0 : applyDate.hashCode());
      result = prime * result + ((paymentInfo == null) ? 0 : paymentInfo.hashCode());
      result = prime * result + price;
      result = prime * result + ((tuteeId == null) ? 0 : tuteeId.hashCode());
      result = prime * result + tutoringApplyId;
      result = prime * result + tutoringId;
      result = prime * result + ((tutoringName == null) ? 0 : tutoringName.hashCode());
      return result;
   }
   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      TutoringBuyVO other = (TutoringBuyVO) obj;
      if (applyDate == null) {
         if (other.applyDate != null)
            return false;
      } else if (!applyDate.equals(other.applyDate))
         return false;
      if (paymentInfo == null) {
         if (other.paymentInfo != null)
            return false;
      } else if (!paymentInfo.equals(other.paymentInfo))
         return false;
      if (price != other.price)
         return false;
      if (tuteeId == null) {
         if (other.tuteeId != null)
            return false;
      } else if (!tuteeId.equals(other.tuteeId))
         return false;
      if (tutoringApplyId != other.tutoringApplyId)
         return false;
      if (tutoringId != other.tutoringId)
         return false;
      if (tutoringName == null) {
         if (other.tutoringName != null)
            return false;
      } else if (!tutoringName.equals(other.tutoringName))
         return false;
      return true;
   }
   
   
}