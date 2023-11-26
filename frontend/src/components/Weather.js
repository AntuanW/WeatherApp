import React, { useEffect, useState } from 'react';
import {getWeather} from "../services/weatherAPIService.js";
import WbSunnyOutlinedIcon from '@mui/icons-material/WbSunnyOutlined';
import CloudOutlinedIcon from '@mui/icons-material/CloudOutlined';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome'
import ThunderstormOutlinedIcon from '@mui/icons-material/ThunderstormOutlined';
import WaterDropOutlinedIcon from '@mui/icons-material/WaterDropOutlined';
import AcUnitOutlinedIcon from '@mui/icons-material/AcUnitOutlined';
import { solid } from '@fortawesome/fontawesome-svg-core/import.macro';

const Weather = () => {
    const [weather, setWeather] = useState();
    const [temperatureColor, setTemperatureColor] = useState("black");

    useEffect(()=> {
        getWeather().then((res) => {
            setWeather(res)
            switch (res.temperatureScale) {
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
        })
    }, [])

    const getWeatherEmote = (emote) => {
        switch (emote) {
            case 'CLEAR':
                return <WbSunnyOutlinedIcon sx={{ fontSize: 100, color: '#ffea00' }}/>
            case 'CLOUDY':
                return <CloudOutlinedIcon sx={{ fontSize: 100, color: '#7EC8E3' }}/>
            case 'FOGGY':
                return <FontAwesomeIcon icon={solid("smog")} style={{color: "grey", fontSize: 100}} />
            case 'THUNDERY':
                return <ThunderstormOutlinedIcon sx={{ fontSize: 100, color: "#050A30" }}/>
            case 'RAINY':
                return <WaterDropOutlinedIcon sx={{ fontSize: 100, color: "#050A30"}}/>
            case 'SLEETY':
                return <FontAwesomeIcon icon={solid("cloud-meatball")} style = {{color: "#7EC8E3", fontSize: 100}} />
            case 'SNOWY':
                return <AcUnitOutlinedIcon sx={{ fontSize: 100 }}/>
            case 'HAIL':
                return <FontAwesomeIcon icon={solid("cloud-meatball")} style = {{color: "#7EC8E3", fontSize: 100}} />
            default:
                return <></>
        }
    }
    
    return (
        <div className='weather'>
            {getWeatherEmote(weather?.forecast)}
            <p id = "temperature" style={{color: temperatureColor}}><b>{weather?.temperature}&deg;C</b></p>
            <p><b>Air quality:</b> {weather?.airQuality}</p>
        </div>
    );
};

export default Weather;