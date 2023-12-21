import axios from 'axios'

const getWeather = () => {
    return new Promise((resolve, reject) => {
        axios.get("http://localhost:8080/weatherapp/weather", {
        headers: {'Access-Control-Allow-Credentials':true,
        'Authorization': localStorage.getItem("token")},
        responseType: "json",
        })
        .then(res => resolve(res.data))
        .catch(err => reject(err))
    })
}

const getWardrobe = () => {
    return new Promise((resolve, reject) => {
        axios.get("http://localhost:8080/weatherapp/wardrobe", {
        headers: {'Access-Control-Allow-Credentials':true,
        'Authorization': localStorage.getItem("token")},
        responseType: "json",
        })
        .then(res => resolve(res.data))
        .catch(err => {reject(err)})
    })
}

const postLocation = (data) => {
    return new Promise((resolve, reject) => {
        axios.post("http://localhost:8080/users/configuration/location", 
        data, {
            headers: {'Access-Control-Allow-Credentials':true,
            'Authorization': localStorage.getItem("token")},
            responseType: "json",
        })
        .then(res => resolve(res))
        .catch(err => reject(err))
    })
}

const openUserSession = () => {
    return new Promise((resolve, reject) => {
        if(localStorage.getItem("token") === null) {
            axios.post("http://localhost:8080/users/configuration/user", 
            {headers: {
                    'Access-Control-Allow-Credentials':true
                },
                responseType: "json",
            })
            .then(res => {
                localStorage.setItem("token", res.data.token);
                resolve(res)})
            .catch(err => reject(err))
        }

        resolve(localStorage.getItem("token"))
    })
}

export {getWeather, getWardrobe, postLocation, openUserSession}