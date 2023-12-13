import React from 'react';

import { Typography } from '@mui/material';
import Input from './Input';

const LocationInput = (props) => {
    const { title, latitudeLabel, longitudeLabel } = props;

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
        <>
        <Typography variant="h10" sx={{ marginTop: 2, color: '#0e0f3b',  textAlign: 'center' }}>{title}</Typography>
        <Input label={latitudeLabel} id={latitudeLabel}  validation={latitudeValidation} />
        <Input label={longitudeLabel}  id={longitudeLabel}  validation={longitudeValidation} />
        </>
    );
};

export default LocationInput;