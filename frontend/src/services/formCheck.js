const isValid = error =>{
    console.log("valid")
    console.log(error)
    console.log(Object.keys(error))
    return !(Object.keys(error).length > 0);
}

const findInputErrors = (errors, id) => {
    return Object.keys(errors)
    .filter(key => key === id)
    .reduce((cur, key) => {return Object.assign(cur, {error: errors[key]})}, {});
}

export {isValid, findInputErrors};