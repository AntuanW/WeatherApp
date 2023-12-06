import axios from 'axios'

const headersConfig = {
    'Access-Control-Allow-Credentials':true,
    'Authorization': "aa"
}

const getWeather = () => {
    return new Promise((resolve, reject) => {
        axios.get("http://localhost:8080/weatherapp/weather", {
        headers: headersConfig,
        responseType: "json",
        })
        .then(res => resolve(res.data))
        .catch(err => reject(err))
    })
}

const getWardrobe = () => {
    return new Promise((resolve, reject) => {
        axios.get("http://localhost:8080/weatherapp/wardrobe", {
        headers: headersConfig,
        responseType: "json",
        })
        .then(res => resolve(res.data))
        .catch(err => {reject(err)})
    })
}

const postLocation = (data) => {
    console.log(data);

    return new Promise((resolve, reject) => {
        axios.post("http://localhost:8080/users/configuration/location", 
        data, {
            headers: headersConfig,
            responseType: "json",
        })
        .then(res => resolve(res))
        .catch(err => reject(err))
    })
}

const openUserSession = () => {
    return new Promise((resolve, reject) => {
        axios.post("http://localhost:8080/users/configuration/user", 
        {headers: {
                'Access-Control-Allow-Credentials':true
            },
            responseType: "json",
        })
        .then(res => resolve(res))
        .catch(err => reject(err))
    })
}

export {getWeather, getWardrobe, postLocation, openUserSession}