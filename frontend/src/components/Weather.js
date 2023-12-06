import React, { useEffect, useState } from 'react';
import {getWeather} from "../services/weatherAPIService.js";
import WbSunnyOutlinedIcon from '@mui/icons-material/WbSunnyOutlined';
import CloudOutlinedIcon from '@mui/icons-material/CloudOutlined';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome'
import ThunderstormOutlinedIcon from '@mui/icons-material/ThunderstormOutlined';
import WaterDropOutlinedIcon from '@mui/icons-material/WaterDropOutlined';
import AcUnitOutlinedIcon from '@mui/icons-material/AcUnitOutlined';
import {faCloudMeatball, faSmog} from '@fortawesome/free-solid-svg-icons'

const Weather = () => {
    const [weather, setWeather] = useState();
    const [temperatureColor, setTemperatureColor] = useState("black");
    const [error, setError] = useState(false);

    useEffect(()=> {
        getWeather().then((res) => {
            setWeather(res)
            setColor(res.temperatureScale)
            setError(false);
        }).catch((err) => {
            setError(true);
            console.log(err);
        })
    }, [])

    const setColor = (temperature) => {
        switch (temperature) {
            case 'FREEZING':
                setTemperatureColor("#7EC8E3");
                break;
            case 'COLD':
                setTemperatureColor("#0000FF");
                break;
            case 'WARM':
                setTemperatureColor("green");
                break;
            case 'HOT':
                setTemperatureColor("red");
                break;
            default:
                break;
        }
    }

    const weatherIcon = {
        CLEAR: <WbSunnyOutlinedIcon sx={{ fontSize: 100, color: '#ffea00' }}/>,
        CLOUDY: <CloudOutlinedIcon sx={{ fontSize: 100, color: '#7EC8E3' }}/>,
        FOGGY: <FontAwesomeIcon icon={faSmog} style={{color: "grey", fontSize: 100}} />,
        THUNDERY: <ThunderstormOutlinedIcon sx={{ fontSize: 100, color: "#050A30" }}/>,
        RAINY: <WaterDropOutlinedIcon sx={{ fontSize: 100, color: "#050A30"}}/>,
        SLEETY: <FontAwesomeIcon icon={faCloudMeatball} style = {{color: "#7EC8E3", fontSize: 100}} />,
        SNOWY: <AcUnitOutlinedIcon sx={{ fontSize: 100, color: "#7EC8E3" }}/>,
        HAIL: <FontAwesomeIcon icon={faCloudMeatball} style = {{color: "#7EC8E3", fontSize: 100}} />
    }
    const getWeatherEmote = (emote) => weatherIcon[emote];
    
    return (
        <div className='weather'>
            <h3 style={{ textTransform: 'uppercase' }}>{weather?.locationName}</h3>
            {error && <p style = {{color: "red"}} >No data for provided arguments</p>}
            {getWeatherEmote(weather?.forecast)}
            <p id = "temperature" style={{color: temperatureColor}}><b>{weather?.temperature}&deg;C</b></p>
            <p><b>Air quality:</b> {weather?.airQuality}</p>
        </div>
    );
};

export default Weather;