
public enum State {


     NO_HIT ('M'),
     PARTIAL_HIT ('H'),
     SUNK('S');

     private char value;
      private State(char value) {
           this.value=value;
     }
     public char getChar(){
           return  value;
     }

}