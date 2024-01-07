import React from 'react';

import { IconButton, Typography } from '@mui/material';
import CoordinatesInput from './CoordinatesInput';
import NameInput from './NameInput';
import TimeInput from './TimeInput';
import DeleteIcon from '@mui/icons-material/Delete';

const LocationInput = (props) => {
    const { title, latitudeLabel, longitudeLabel, timeLabel, nameLabel, index, remove, replace } = props;

    const latitudeValidation = {
        required: {
            value: true,
            message: 'This field is required',
        },
        min: {
            value: -90,
            message: 'Latitude should be greater than -90'
        },
        max: {
            value: 90,
            message: 'Latitude should be less than 90'
        }
    }

    const longitudeValidation = {
        required: {
            value: true,
            message: 'This field is required',
        },
        min: {
            value: -180,
            message: 'Longitude should be greater than -180'
        },
        max: {
            value: 180,
            message: 'Longitude should be less than 180'
        }
    }


    return (
        <div className = "locationInput">
        <Typography variant="h10" sx={{ marginTop: 2, color: '#0e0f3b',    textAlign: 'center', fontWeight: 'bold' }}>{title}
            <IconButton aria-label="delete" size="large"
                onClick={() => remove(index)}>
                <DeleteIcon fontSize="inherit" />
            </IconButton>
        </Typography>
        <CoordinatesInput id={latitudeLabel}  validation={latitudeValidation} index = {index}/>
        <CoordinatesInput id={longitudeLabel}  validation={longitudeValidation} index = {index}/> 
        <TimeInput id={timeLabel} index = {index}/>
        <NameInput id={nameLabel} index = {index} replace = {replace}/>
        </div>
    );
};

export default LocationInput;