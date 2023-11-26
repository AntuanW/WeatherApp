const isValid = error =>{
    return !(Object.keys(error).length > 0);
}

const findInputErrors = (errors, label) => {
    return Object.keys(errors)
    .filter(key => key.includes(label))
    .reduce((cur, key) => {return Object.assign(cur, {error: errors[key]})}, {});
}

export {isValid, findInputErrors};