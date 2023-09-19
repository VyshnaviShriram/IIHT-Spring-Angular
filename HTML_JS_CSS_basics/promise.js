const fun1=()=>{
    return "This is function1";
}
// const fun2=()=>{
//     setTimeout( ()=>{
//         return "This is function2";
//     },5000);
// }
const resolvefun2=()=>{
    return new Promise((resolve,reject)=>{
        setTimeout( ()=>{
            resolve ("This is function2");
        },2000);
    });
};
const fun2=()=>{
    
        setTimeout( ()=>{
            return "This is function2";
        },2000);
};
const fun3=()=>{
    return "This is function3";
}
const callFunc= ()=>{
    let val1 =fun1();
    console.log(val1);
    let val2 =resolvefun2();
    console.log(val2);
    let val3 =fun3();
    console.log(val3);
}

const callFunc1= async()=>{
    let val1 =fun1();
    console.log(val1);
    let val2 =await resolvefun2();
    console.log(val2);
    let val3 =fun3();
    console.log(val3);
}