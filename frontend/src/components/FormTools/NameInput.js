import React, { useEffect, useState } from 'react';
import { TextField, Box, FormControlLabel, Checkbox } from '@mui/material';
import { useFormContext } from 'react-hook-form';
import { isValid, findInputErrors } from "../../services/formCheck.js"
import BookmarkBorderIcon from '@mui/icons-material/BookmarkBorder';
import BookmarkIcon from '@mui/icons-material/Bookmark';

const NameInput = (props) => {
    const { id, index, replace } = props;

    const {
        register,
        setValue,
        formState: { errors },
    } = useFormContext();

    const inputError = findInputErrors(errors, index, id);
    const isInvalid = !isValid(inputError);

    const [saveLocation, setSaveLocation] = useState(false);

    const handleCheckboxChange = (event) => {
        setSaveLocation(event.target.checked);
    }

    useEffect(() => {
        setValue(`location.${index}.name`, "");
    }, [saveLocation, setSaveLocation]);

    return (
        <Box display="flex" alignItems="center">
            <FormControlLabel
                style={{ margin: "8px 10px 0 -14px" }}
                value="end"
                control={
                <Checkbox
                    id={id}
                    checked={saveLocation}
                    onChange={handleCheckboxChange}
                    icon={<BookmarkBorderIcon />}
                    checkedIcon={<BookmarkIcon />}
                    variant="h10" 
                    sx={{color: '#0e0f3b',  textAlign: 'center' }}
                    inputProps={{ 'aria-label': 'save location checkbox' }}
                /> 
            }
                label="Save location"
                labelPlacement="end"
            />
            {saveLocation && (
                <TextField
                    sx={{ color: '#0e0f3b', textAlign: 'center' }}
                    variant="standard"
                    id={`${id}${index}`}
                    label={id}
                    style={{ margin: "-4px 0 10px 0" }}
                    disabled={!saveLocation}
                    {...register(`location.${index}.${id}`, { required: 'Location name is required' })}
                    helperText={inputError.error?.message}
                    error = {isInvalid}
                    required={true}
                />
            )}
        </Box>
    );
};

export default NameInput;
