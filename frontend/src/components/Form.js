import React, { useRef } from 'react';
import { FormControl, Button, Typography } from '@mui/material';
import { useFieldArray, useForm } from "react-hook-form";
import { FormProvider } from 'react-hook-form';
import "./styles/form.css";
import { useNavigate } from "react-router-dom";
import { postLocation } from '../services/weatherAPIService';
import LocationSection from './FormTools/LocationSection';
import LocationDropdown from './FormTools/LocationDropdown';

const Form = () => {
    const methods = useForm({
        defaultValues: { location: [{ name: '', latitude: '', longitude: '', time: '' }] },
      });

    let control = methods.control;
    const handleSubmit = methods.handleSubmit;

    const { fields, append, update, remove, replace } = useFieldArray({
        control,
        name: 'location'
      });

    const navigate = useNavigate();

    const locationSectionRef = useRef();

    const useSelectLocation = (location) => {
        locationSectionRef.current.useSavedLocation(location);
      };

    const onSubmit = handleSubmit(data => {
        console.log(data.location)
        postLocation(data.location).then((res) => {
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
            style={{ width: "500px" }}
            margin="normal"
            onSubmit={e => e.preventDefault()}
            noValidate
            className="container"
        >
            <Typography variant="h5" sx={{ marginBottom: 3, color: '#0e0f3b', textAlign: 'center', textShadow: '1px 1px 1px #fff' }}> 
            Don't know what to wear?</Typography>
            <LocationDropdown pasteSelectLocation={useSelectLocation}/>
            <LocationSection 
                ref = {locationSectionRef}
                control={control}
                append={append}
                fields={fields}
                remove={remove}
                replace={replace}/>
            <Button disabled = {fields.length == 0} size='large' variant="contained" onClick={onSubmit} sx={{ marginTop: 2 }}>Show weather</Button>
        </FormControl>
        </FormProvider>
    </div>
    );
};

export default Form;