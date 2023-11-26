import React, { useEffect, useState } from 'react';
import {getWeather} from '../services/weatherAPIService.js';
import {  Button } from '@mui/material';
import { useNavigate } from "react-router-dom";

const Weather = () => {
    const [weather, setWeather] = useState();

    const navigate = useNavigate();

    const onClick =() => {
        navigate("/", { replace: false });
    }

    useEffect(()=> {
        setWeather(getWeather());
    }, [])
    
    return (
        <div className='weather'>
            {weather?.temperature}
            {weather?.airQuality}
            {weather?.forecast}
            <Button variant="outlined" onClick={onClick}>Change location</Button>
        </div>
    );
};

export default Weather;