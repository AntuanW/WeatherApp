const isValid = error =>{
    return !(Object.keys(error).length > 0);
}

const findInputErrors = (errors, id) => {
    return Object.keys(errors)
    .filter(key => key.includes(id))
    .reduce((cur, key) => {return Object.assign(cur, {error: errors[key]})}, {});
}

export {isValid, findInputErrors};