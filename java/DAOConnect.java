public ArrayList getTeamDetail(String team) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "select USER_ID,USER_NAME,NO_OF_DAYS,START_DATE,END_DATE,REASON,APPROVE from table WHERE USER_ID IN(select USER_SHORT_ID from RMG_USER WHERE TEAM_NAME='" + team + "')and Month(START_DATE) = Month(Now()) and Year(START_DATE) = Year(Now()) ORDER BY START_DATE ASC";
        ArrayList detail = new ArrayList();
        try {
           DAOConnection conn = new DAOConnection();
            Connection connDAO = conn.connect();
            ps = connDAO.prepareStatement(query);
            rs = ps.executeQuery();
            detail = buid(rs);
            connDAO.close();
            return detail;
        } catch (SQLException err) {
            Logger.getLogger(RMGUserLogin.class.getName()).log(Level.SEVERE, null, err);
        }
        return detail;
    }
	
	public ArrayList getPrevoiusDetail(String userId, String month) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "select USER_ID,USER_NAME,NO_OF_DAYS,START_DATE,END_DATE,REASON,APPROVE from table WHERE USER_ID='"+userId+"' and MONTH(START_DATE) =" + month + " and Year(START_DATE) = Year(Now()) ORDER BY START_DATE ASC";
        ArrayList detail = new ArrayList();
        try {
            DAOConnection conn = new DAOConnection();
            Connection connDAO = conn.connect();
            ps = connDAO.prepareStatement(query);
            rs = ps.executeQuery();

            detail = buid(rs);

            ps.close();
            connDAO.close();


        } catch (SQLException err) {
            Logger.getLogger(RMGUserLogin.class.getName()).log(Level.SEVERE, null, err);

        }
        return detail;
    }
