/**
 * Represents a high Score in skiBall
 *
 */
public class highScore implements Comparable<highScore>
{
    public String name;
    public int score;

    /** Default constructor
     * 
     */
    public highScore(){
        name = null;
        score = -1;
    }

    /**
     *  @param nameIn the value to set name to
     *  @param scoreIn the value to set score to
     */
    public highScore(String nameIn, int scoreIn){
        name = nameIn;
        score = scoreIn;
    }

    /** Returns a full string object with labels of this object
     *  @return a full string representation of this object
     */
    public String toString()
    {
        return "Name: "+name+" Score: "+score;
    }

    /** Returns a simplified version of data, like an old arcade game format
     *  @return a simplified version of this object
     */
    public String toSimpleString()
    {
        return score+"\t"+name;
    }

    /** Returns 0 if this and other score are equal numerically, 1 if this is greater,
     *  -1 else.
     * 
     *  @param other a highScore object to compare to this.
     *  @returns 0 if this and other score are equal numerically, 1 if this is greater,
     *  -1 else.
     */
    @Override
    public int compareTo(highScore other)
    {
        if(this.score == other.score){return 0;}
        else if(this.score > other.score){return 1;}
        else{return -1;}
    }

}