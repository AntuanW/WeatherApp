const isValid = error =>{
    return !(Object.keys(error).length > 0);
}

const findInputErrors = (errors, id, name) => {
    if(typeof errors.location === 'undefined' || errors.location === null){
        return {}
    }

    let aa = Object.keys(errors.location)
    .filter(key => key == id)
    .reduce((cur, key) => {
        const error = errors.location[key][name];
        if (error) {
            return Object.assign(cur, { error: error });
        }
        return cur;
    }, {});

    return aa;
}

export {isValid, findInputErrors};