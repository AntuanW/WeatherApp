import React, { useState } from 'react';
import { TextField, Box, FormControlLabel } from '@mui/material';
import SaveCheckBox from './SaveCheckBox';
import { useFormContext } from 'react-hook-form';

const NameInput = (props) => {
    const { label, id } = props;

    const {
        register,
        setLocationName,
        formState: { errors },
    } = useFormContext();


    const [saveLocation, setSaveLocation] = useState(false);

    const handleLocationNameChange = (event) => {
        setLocationName(event.target.value);
    };

    return (
        <Box display="flex" alignItems="center">
            <FormControlLabel
                style={{ margin: "8px 10px 0 -14px" }}
                value="end"
                control={<SaveCheckBox setSaveLocation={setSaveLocation} saveLocation={saveLocation} />}
                label="Save location"
                labelPlacement="end"
            />
            {saveLocation && (
                <TextField
                    sx={{ color: '#0e0f3b', textAlign: 'center' }}
                    variant="standard"
                    id={id}
                    label={label}
                    onChange={handleLocationNameChange}
                    style={{ margin: "-4px 0 10px 0" }}
                    disabled={!saveLocation}
                    {...register(id, { required: 'Location name is required' })}
                    error={!!errors[id]}
                    helperText={errors[id]?.message}
                    required={true}
                />
            )}
        </Box>
    );
};

export default NameInput;
