import React, { useState, useEffect } from 'react';
import { getWardrobe } from '../services/weatherAPIService.js';
import MasksOutlinedIcon from '@mui/icons-material/MasksOutlined';
import UmbrellaOutlinedIcon from '@mui/icons-material/UmbrellaOutlined';
import { Paper } from '@mui/material';
import CloseOutlinedIcon from '@mui/icons-material/CloseOutlined';

const Wardrobe = () => {
    const [wardrobe, setWardrobe] = useState();

    useEffect(()=> {
        getWardrobe().then((res) => {
            setWardrobe(res)
            console.log(res)
        })
    }, [])

    const wardrobeStyle = {
        elevation: 3,
        className: 'clothes',
        sx: {backgroundColor: "whitesmoke"}
    }

    return (
        <div id = "clothes-container">
            <div id = "items">
                <div style={{position: "relative"}}>
                    <MasksOutlinedIcon
                        sx={{ fontSize: 100 }}/>
                    {!wardrobe?.gasMask && <CloseOutlinedIcon sx = {{color: "#6f0000", fontSize: 100, position: "absolute", left: 0}}/>}
                </div>

                <div style={{position: "relative"}}>
                    <UmbrellaOutlinedIcon
                        sx={{ fontSize: 100 }}/>
                    {!wardrobe?.umbrella && <CloseOutlinedIcon sx = {{color: "#6f0000", fontSize: 100, position: "absolute", right: 0}}/>}
                </div>
            </div>
            
            <Paper {...wardrobeStyle}>
                <h2>Top</h2>
                <ul>
                {wardrobe?.clothes?.top.map((item, i) =>
                    <li key = {i}>{item}</li>
                )}
                </ul>
            </Paper>
            <Paper {...wardrobeStyle}>
                <h2>Bottom</h2>
                <ul><li>{wardrobe?.clothes?.trousers}</li></ul>
            </Paper>
            <Paper {...wardrobeStyle}>
                <h2>Shoes</h2>
                <ul><li>{wardrobe?.clothes?.shoes}</li></ul>
            </Paper>
            <Paper {...wardrobeStyle}>
                <h2>Accessories</h2>
                <ul>{wardrobe?.clothes?.accessories.map((item, i) =>
                    <li key = {i}>{item}</li>
                )}
                </ul>
            </Paper>
        </div>
    );
};

export default Wardrobe;