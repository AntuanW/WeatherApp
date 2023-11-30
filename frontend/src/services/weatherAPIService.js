import axios from 'axios'

const getWeather = () => {
    return new Promise((resolve, reject) => {
        axios.get("http://localhost:8080/weatherapp/weather", {
        headers: {
            'Access-Control-Allow-Credentials':true
        },
        responseType: "json",
        })
        .then((res) => {
            resolve(res.data)})
        .catch(err => console.log(err))
    })
}

const getWardrobe = () => {
    return new Promise((resolve, reject) => {
        axios.get("http://localhost:8080/weatherapp/wardrobe", {
        headers: {
            'Access-Control-Allow-Credentials':true
        },
        responseType: "json",
        })
        .then((res) => {
            resolve(res.data)})
        .catch(err => console.log(err))
    })
}

const postLocation = (data) => {
    console.log(data);

    return new Promise((resolve, reject) => {
        axios.post("http://localhost:8080/users/configuration/location", 
        data, {
            headers: {
                'Access-Control-Allow-Credentials':true
            },
            responseType: "json",
        })
        .then((res) => {console.log(res)
            resolve(res)})
        .catch(err => console.log(err))
    })
}

export {getWeather, getWardrobe, postLocation}