import React from 'react';
import { FormControl, Button, Typography } from '@mui/material';
import { useForm } from "react-hook-form";
import { FormProvider } from 'react-hook-form';
import Input from './Input';
import "./styles/form.css";
import { useNavigate } from "react-router-dom";
import { postLocation } from '../services/weatherAPIService';

const Form = () => {
    const methods = useForm();
    const navigate = useNavigate();

    const onSubmit = methods.handleSubmit(data => {
        postLocation(methods.getValues()).then((res) => {
            console.log(res);
            navigate("/weather", { replace: false });
        })
    })

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
        <div className='form'>
        <FormProvider {...methods}>
            <FormControl
                sx={{ border: 1, borderColor: 'grey.500', borderRadius: 1, p: 4, backgroundColor: "whitesmoke"}}
                style = {{width: "400px"}}
                margin="normal"
                onSubmit={e => e.preventDefault()}
                noValidate
                className="container">
                <Typography variant="h5" sx={{ marginBottom: 3, color: '#0e0f3b', textAlign: 'center', textShadow: '1px 1px 1px #fff' }}> Don't know what to wear?</Typography>
                <Input
                    label = "latitude"
                    id = "latitude"
                    validation = {latitudeValidation}/>
                <Input
                    label = "longitude"
                    id = "longitude"
                    validation = {longitudeValidation}/>
                <Button variant="outlined" onClick={onSubmit}>Show weather</Button>
            </FormControl>
        </FormProvider>
        </div>
    );
};

export default Form;