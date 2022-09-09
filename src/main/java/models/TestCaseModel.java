package models;


public class TestCaseModel {
    public Integer id;
    public String title;
    public Integer sectionID;
    public Integer templateID;
    public Integer typeID;
    public Integer priority_id;
    public Object milestoneID;
    public Object refs;
    public Integer createdBy;
    public Integer createdOn;
    public Integer updatedBy;
    public Integer updatedOn;
    public Object estimate;
    public Object estimateForecast;
    public Integer suiteID;
    public Integer displayOrder;
    public Integer custom_team;
    public Integer custom_auto;
    public Integer customStatus;
    public Object customTag;
    public String customPreconds;
    public Object customTestData;
    public String customSteps;
    public String customExpected;
    public Object customStepsSeparated;
    public Object customMission;
    public Object customGoals;



    @Override
    public String toString() {
        return "TestCaseModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", sectionID=" + sectionID +
                ", templateID=" + templateID +
                ", typeID=" + typeID +
                ", priorityID=" + priority_id +
                ", milestoneID=" + milestoneID +
                ", refs=" + refs +
                ", createdBy=" + createdBy +
                ", createdOn=" + createdOn +
                ", updatedBy=" + updatedBy +
                ", updatedOn=" + updatedOn +
                ", estimate=" + estimate +
                ", estimateForecast=" + estimateForecast +
                ", suiteID=" + suiteID +
                ", displayOrder=" + displayOrder +
                ", custom_team=" + custom_team +
                ", customAuto=" + custom_auto +
                ", customStatus=" + customStatus +
                ", customTag=" + customTag +
                ", customPreconds='" + customPreconds + '\'' +
                ", customTestData=" + customTestData +
                ", customSteps='" + customSteps + '\'' +
                ", customExpected='" + customExpected + '\'' +
                ", customStepsSeparated=" + customStepsSeparated +
                ", customMission=" + customMission +
                ", customGoals=" + customGoals +
                '}';
    }
}