public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (getNoOfDays() == null || getNoOfDays().length() < 1||getStartDate() == null || getStartDate().length() < 1||getEndDate() == null || getEndDate().length() < 1) {
            errors.add("Enter", new ActionMessage("error.name.required"));
            // TODO: add 'error.name.required' key to your resources
        }
        return errors;
    }

    public String getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(String error) {
        this.error = error;
    }

   
    public String getNoOfDays() {
        return noOfDays;
    }

    /**
     * @param noOfDays the noOfDays to set
     */
    public void setNoOfDays(String noOfDays) {
        this.noOfDays = noOfDays;
    }

    /**
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason the reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * @return the applied
     */
    public String getApplied() {
        return applied;
    }

  
    public void setApplied(String applied) {
        this.applied = applied;
    }

    
    public String getSubmit() {
        return submit;
    }

 
    public void setSubmit(String submit) {
        this.submit = submit;
    }

   
    public String getEdit() {
        return edit;
    }

  
    public void setEdit(String edit) {
        this.edit = edit;
    }

    /**
     * @return the day
     */
    public String getDay() {
        return day;
    }

    /**
     * @param day the day to set
     */
    public void setDay(String day) {
        this.day = day;
    }
