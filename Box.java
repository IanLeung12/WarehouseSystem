public class Box {
    int boxId;
    int weight;
    int height;
    int length;
    int width;

    int posXInTruck;
    int positionYInTruck;

    void rotate(){
        int tempLength;
        tempLength = this.length;
        this.length = this.width;
        this.width = tempLength;
    }
    
}
