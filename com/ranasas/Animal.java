package ranasas;

public abstract class Animal {
    public String name;
    public int positionInTheVector;

    public int getPositionInTheVector(){
        return this.positionInTheVector;
    }
    public void setPositionInTheVector(int positionInTheVector){
         this.positionInTheVector=positionInTheVector;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return this.name;
    }

    public abstract int jump(Animal[] vector);

    public abstract boolean isSapo();

}
