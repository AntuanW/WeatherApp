import React, { useImperativeHandle, useState } from 'react';
import { Button, IconButton, Typography } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';

import LocationInput from './LocationInput';
import { useFormContext } from 'react-hook-form';

const LocationSection = React.forwardRef((props, ref) => {
    const {control, append, fields, remove, replace} = props

    useImperativeHandle(ref, () => ({

        useSavedLocation(location) {
            append({ name: location.name, latitude: location.latitude, longitude: location.longitude, time: location.time })
        }
    
      }));

    return (
        <>
            {fields.map((field, i) => (
                <LocationInput
                    title={"Location " + i}
                    latitudeLabel={"latitude"}
                    longitudeLabel={"longitude"}
                    timeLabel={"time"}
                    nameLabel={"name"}
                    key = {field.id}
                    index={i}
                    remove = {remove}
                    replace = {replace}
                /> 
            ))}
            
            <Button 
                disabled = {fields.length >= 5}
                variant="outlined" 
                onClick={() => append({ name: '', latitude: '', longitude: '', time: '' })
            } 
            sx={{ marginTop:2 }}>Add Location</Button>
        </>
    );
});

export default LocationSection;