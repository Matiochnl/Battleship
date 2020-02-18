public class Square {
    private String shipName = "ocean";
    private String myStatus = " ";
    private String opponentStatus = " ";


    public Square() {
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public void setMyStatus(String myStatus) {
        this.myStatus = myStatus;
    }

    public void setOpponentStatus(String opponentStatus) {
        this.opponentStatus = opponentStatus;
    }

    public String getShipName() {
        return shipName;
    }

    public String getMyStatus() {
        return myStatus;
    }

    public String getOpponentStatus() {
        return opponentStatus;
    }
}
