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
        .then(res => {resolve(res.data)})
        .catch(err => {reject(err)})
    })
}

const postLocation = (data) => {
    console.log(data)  
    let locationData = Object.values(data);  

    let dataToSend = [];

    for(let i = 0; i < locationData.length; i+=2) {
        if(locationData[i] === '') break

        dataToSend.push({"latitude": locationData[i], "longitude": locationData[i + 1]});
    }

    console.log(dataToSend)

    return new Promise((resolve, reject) => {
        axios.post("http://localhost:8080/users/configuration/location", 
        dataToSend, {
            headers: {'Access-Control-Allow-Credentials':true,
            'Authorization': localStorage.getItem("token")},
            responseType: "json",
        })
        .then(res => resolve(res))
        .catch(err => reject(err))
    })
}

const checkUser = () => {
    //console.log(localStorage.getItem("token"))
    return new Promise((resolve, reject) => {
        axios.post("http://localhost:8080/users/configuration/checkUser", "aa",
        {
            headers: {
                'Access-Control-Allow-Credentials':true,
                'Authorization': localStorage.getItem("token")
            },
            responseType: "json",
        })
        .then(res => {
            resolve(res)})
        .catch(err => {
            console.log(err);
            if(err.response.status === 401) {
                console.log("new token")
                localStorage.removeItem("token")
            }
            reject(err)
        })
    })
}

const openUserSession = () => {
    return new Promise((resolve, reject) => {
        checkUser().then()
        .catch(e => console.log(e))
        .finally(() => {
            if(localStorage.getItem("token") === null) {
                axios.post("http://localhost:8080/users/configuration/user", 
                {headers: {
                        'Access-Control-Allow-Credentials':true,
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
    })
}

export {getWeather, getWardrobe, postLocation, openUserSession}