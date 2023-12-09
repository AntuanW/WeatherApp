const isValid = error =>{
    console.log("valid")
    console.log(error)
    console.log(Object.keys(error))
    return !(Object.keys(error).length > 0);
}

const findInputErrors = (errors, id) => {
    let aa = Object.keys(errors)
    .filter(key => key === id)
    .reduce((cur, key) => {return Object.assign(cur, {error: errors[key]})}, {});
    console.log(id);
    console.log(aa);
    return Object.keys(errors)
    .filter(key => key === id)
    .reduce((cur, key) => {return Object.assign(cur, {error: errors[key]})}, {});
}

export {isValid, findInputErrors};