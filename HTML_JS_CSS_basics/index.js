var Name = 'Vyshnavi'
        var age = 22;
        console.log("Name is " + Name + " and age is " + age)


        function alertUser() {
            var favChips = "LaysBlue";
            console.log("User is logged in");
           // alert("The user logged in is: " + Name);
        }
    alertUser();

    function operations(num){
        if(num >20)
            console.log("Number is greater than 20");
        else if(num <20)
            console.log("Number is less than 20");
        else
            console.log("Number is 20");
    }
    operations(24);

    function creditScore(score){
        var credLimit;
        switch(true){
            case score>800 : credLimit="Limit is above 1lac";
            break;
            case 300<score<800 : credLimit="Limit is between 50thousand to 1lac";
             break;
             default : credLimit ="Below 50 Thousands";
             break;
        }
        console.log(credLimit);
    }
    
    creditScore(450);
console.log("Log checker");
//console.log(a+"   "+b);
var a=12;
let b=13;
const c=14;
a++;
b++;
console.log(a+"   "+b);

class vehicle{
    display(){
        console.log("This vehicle is ");
    }
}
class cars extends vehicle{
    constructor(brand,Model,prize){
        super();
        this.Model=Model;
        this.brand=brand;
        this.prize=prize;
    }

    print(){
        console.log("This is a :"+this.brand+"  "+this.Model+"  "+this.prize);

    }
}
var C1=new cars("Mercedes","A Class",400000);
var c2=new cars("BMW","3-series",5000000);
C1.print()
c2.print();
c2.display();

class Car {
    constructor(brand) {
      this.carname = brand;
    }
    present() {
      return 'I have a ' + this.carname;
    }
  }
  
  class Model extends Car {
    constructor(brand, mod) {
      super(brand);
      this.model = mod;
    }
    show() {
      return this.present() + ', it is a ' + this.model;
    }
  }
  
  mycar = new Model("Ford", "Mustang");
 console.log(mycar.show());


 let evenNumbers=[2,4,0,6,8,10];
 let oddNumbers=[1,3,5,7,9];
 let numbers=[...oddNumbers,...evenNumbers,oddNumbers];    //.... is a spread operator
 console.log("Numbers are : "+numbers);

