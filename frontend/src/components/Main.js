import React from 'react';
import Weather from './Weather';
import Wardrobe from './Wardrobe';
import {  Button } from '@mui/material';
import { useNavigate } from "react-router-dom";
import "./styles/main.css"


const Main = () => {
    const navigate = useNavigate();

    const onClick =() => {
        navigate("/", { replace: false });
    }


    return (
        <main>
            <Button sx={{left: 30}} variant="outlined" onClick={onClick}>Change location</Button>
            <Weather/>
            <Wardrobe/>
        </main>
    );
};

export default Main;