function car(color, model, cost, fuelType) {
    this.color = color;
    this.model = model;
    this.cost = cost;
    this.fuelType = fuelType;
    let runningCostPerYear = 30000

    this.getCarDetails = function () {
        console.log("color is " + this.color);
        console.log("model is " + this.model);
        console.log("cost is " + this.cost);
        console.log("fuelType is " + this.fuelType);
        console.log("runningCostPerYear is " + runningCostPerYear);
    }

    this.TotalCostOfOwnerShip = function () {
        console.log(cost + runningCostPerYear);
    }
}
let MercedesCCLass=new car("Black","C Class",5000000,"Petrol");
MercedesCCLass.getCarDetails();
MercedesCCLass.TotalCostOfOwnerShip();

