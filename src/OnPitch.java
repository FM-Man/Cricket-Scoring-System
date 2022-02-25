public class OnPitch {
    private Player onStrike;
    private Player nonStrike;

    public OnPitch(Player onStrike, Player nonStrike){
        this.nonStrike = nonStrike;
        this.onStrike = onStrike;
        setStrike();
    }

    public Player onStrike() {
        return onStrike;
    }
    public Player nonStrike() {
        return nonStrike;
    }
    public boolean isFull(){
        return onStrike != null && nonStrike != null;
    }

    public void switchStrike(){
        Player player = onStrike;
        onStrike = nonStrike;
        nonStrike = player;
        setStrike();
    }

    public void changeBatter(Player newBatter, boolean isOnStrike){
        if(isOnStrike) {
            onStrike = newBatter;
        } else {
            nonStrike = newBatter;
            switchStrike();
        }
    }

    private void setStrike(){
        onStrike.onStrike();
        nonStrike.nonStrike();
    }

}

