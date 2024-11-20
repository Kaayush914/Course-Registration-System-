class Complaint {
    private String Dscptn;
    private String Status; // Pending or Resolved

    public Complaint(String description) {
        this.Dscptn = description;
        this.Status = "Still Pending";
    }

    public String getDscptn() {
        return Dscptn;
    }

    public String getStatus() {
        return Status;
    }

    public void resolve() {
        Status = "Resolved....";
    }

    public void printComplaint() {
        System.out.println("Description: " + Dscptn);
        System.out.println("Status: " + Status);
    }
}
