import java.util.concurrent.ThreadLocalRandom;

public class randomHouseGenerator
{
    public generatedHouse generateHouse()
    {
        int minFloor = 1;
        int maxFloor = 4;
        int randfloor_No = ThreadLocalRandom.current().nextInt(minFloor, maxFloor +1);

        int minBedroom = 1;
        int maxBedroom = 5;
        int randBedroom = ThreadLocalRandom.current().nextInt(minBedroom, maxBedroom+1);

        int minKitchen = 1;
        int maxKitchen = 3;
        int randKitchen = ThreadLocalRandom.current().nextInt(minKitchen,maxKitchen+1);

        int minBathroom = 1;
        int maxBathroom = 3;
        int randBathroom = ThreadLocalRandom.current().nextInt(minBathroom,maxBathroom+1);

        int noPool = 0;
        int yesPool = 1;
        int yesNoPool = ThreadLocalRandom.current().nextInt(noPool,yesPool+1);

        int noGarage = 0;
        int yesGarage = 1;
        int yesNoGarage = ThreadLocalRandom.current().nextInt(noGarage,yesGarage+1);

        int minPrice = 1000000;
        int maxPrice = 12000000;
        int randPrice = ThreadLocalRandom.current().nextInt(minPrice,maxPrice+1);

        int roundedPrice = roundUp(randPrice);

        int randomHouseId = ThreadLocalRandom.current().nextInt(100000,999999+1);

        generatedHouse a = new generatedHouse(randomHouseId,randfloor_No,randBedroom,randKitchen,randBathroom,yesNoPool,yesNoGarage,roundedPrice);
        return a;
    }

    private static int roundUp(int src)
    {
        int len = String.valueOf(src).length() - 1;
        if (len == 0) len = 1;
        int d = (int) Math.pow((double) 10, (double) len);
        return (src + (d - 1)) / d * d;
    }

}
