class employee{
    constructor(){
        this.companyName='Cognizant';
        
    }
    print(){
        console.log(this.companyName+" "+this.empName+" "+this.exp+"  "+this.dept+" "+this.position+"  "+
        this.doj+"  "+this.location+"  "+this.mail+"  "+this.empId+"   "+this.salary);
    }
}

class developer extends employee{
    constructor(empName,exp,dept,position,doj,location,mail,empId,salary){
        super();  
        this.empName=empName;
        this.exp=exp;
        this.dept=dept;
        this.position=position;
        this.doj=doj;
        this.location=location;
        this.mail=mail;
        this.empId=empId;
        this.salary=salary;      
    }
}

class hr extends employee{
    constructor(empName,exp,dept,position,doj,location,mail,empId,salary){
        super();  
        this.empName=empName;
        this.exp=exp;
        this.dept=dept;
        this.position=position;
        this.doj=doj;
        this.location=location;
        this.mail=mail;
        this.empId=empId;
        this.salary=salary;      
    }
}
class finance extends employee{
    constructor(empName,exp,dept,position,doj,location,mail,empId,salary){
        super();  
        this.empName=empName;
        this.exp=exp;
        this.dept=dept;
        this.position=position;
        this.doj=doj;
        this.location=location;
        this.mail=mail;
        this.empId=empId;
        this.salary=salary;      
    }
}
class sales extends employee{
    constructor(empName,exp,dept,position,doj,location,mail,empId,salary){
        super();  
        this.empName=empName;
        this.exp=exp;
        this.dept=dept;
        this.position=position;
        this.doj=doj;
        this.location=location;
        this.mail=mail;
        this.empId=empId;
        this.salary=salary;      
    }
}
class marketing extends employee{
    constructor(empName,exp,dept,position,doj,location,mail,empId,salary){
        super();  
        this.empName=empName;
        this.exp=exp;
        this.dept=dept;
        this.position=position;
        this.doj=doj;
        this.location=location;
        this.mail=mail;
        this.empId=empId;
        this.salary=salary;      
    }
}
class qa extends employee{
    constructor(empName,exp,dept,position,doj,location,mail,empId,salary){
        super();  
        this.empName=empName;
        this.exp=exp;
        this.dept=dept;
        this.position=position;
        this.doj=doj;
        this.location=location;
        this.mail=mail;
        this.empId=empId;
        this.salary=salary;      
    }
}


var dev = new developer('Vyshnavi',4,'IT','Software Engineer',new Date(2022,11,17),'Hyderabad','abc@gmail.com',123456,200000);
var hremp = new developer('emp1',3,'HR','HR',new Date(2022,11,17),'Hyderabad','abc@gmail.com',123456,200000);
var financeEMp = new developer('emp2',2,'Finance','Software Engineer',new Date(2022,11,17),'Hyderabad','abc@gmail.com',123456,200000);
var salesEmp = new developer('emp3',1,'Sales','Software Engineer',new Date(2022,11,17),'Hyderabad','abc@gmail.com',123456,200000);
var marketingEmp = new developer('emp4',2.5,'Marketing','Software Engineer',new Date(2022,11,17),'Hyderabad','abc@gmail.com',123456,200000);
var qaEMp = new developer('emp5',1.5,'Quality Analyst','Software Engineer',new Date(2022,11,17),'Hyderabad','abc@gmail.com',123456,200000);
dev.print();
hremp.print();
financeEMp.print();
salesEmp.print();
marketingEmp.print();
qaEMp.print();


function dogs(){
    this.dogName="Lab";
}
dogs.prototype.display=function(){
    return "this dog breed is :"+ this.dogName;
}

function Animal(dogName){
    this.dogName=dogName;
}

Animal.prototype=Object.create(dogs.prototype)
let tommy=new Animal("German Sheperd")
console.log(tommy.display())