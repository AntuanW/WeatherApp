import React, { useEffect } from 'react';
import { useFormContext } from 'react-hook-form';
import { isValid, findInputErrors } from "../../services/formCheck.js"
import { TextField } from '@mui/material';

const CoordinatesInput = (props) => {
    const {id, validation, index} = props;

    const { register, formState: { errors } } = useFormContext();
    const inputError = findInputErrors(errors, index, id);
    const isInvalid = !isValid(inputError);

    return (
        <TextField
            variant="standard"
            fullWidth
            style={{ margin: "10px 0" }}
            id={`${id}${index}`}
            type="number"
            label={id}
            InputLabelProps={{ shrink: true }}
            inputProps={{ step: 0.001 }}
            helperText={inputError.error?.message}
            error = {isInvalid}
            required={true}
            {...register(`location.${index}.${id}`, validation)}
        />
    );
};

export default CoordinatesInput;