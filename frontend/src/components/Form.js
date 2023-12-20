import React from 'react';
import { FormControl, Button, Typography } from '@mui/material';
import { useForm } from "react-hook-form";
import { FormProvider } from 'react-hook-form';
import "./styles/form.css";
import { useNavigate } from "react-router-dom";
import { postLocation } from '../services/weatherAPIService';
import LocationSection from './FormTools/LocationSection';

const Form = () => {
    const methods = useForm();
    const navigate = useNavigate();

    const onSubmit = methods.handleSubmit(data => {
        postLocation(methods.getValues()).then((res) => {
            console.log(res);
            navigate("/weather", { replace: false });
        })
        .catch(e => console.log(e))
    })

    return (
    <div className='form'>
        <FormProvider {...methods}>
        <FormControl
            sx={{ border: 1, borderColor: 'grey.500', borderRadius: 1, p: 4, backgroundColor: "whitesmoke" }}
            style={{ width: "400px" }}
            margin="normal"
            onSubmit={e => e.preventDefault()}
            noValidate
            className="container"
        >
            <Typography variant="h5" sx={{ marginBottom: 3, color: '#0e0f3b', textAlign: 'center', textShadow: '1px 1px 1px #fff' }}> 
            Don't know what to wear?</Typography>
            <LocationSection />
            <Button variant="outlined" onClick={onSubmit} sx={{ marginTop: 2 }}>Show weather</Button>
        </FormControl>
        </FormProvider>
    </div>
    );
};

export default Form;