import React from 'react';
import { Link, useNavigate, useParams} from 'react-router-dom';
import {useState} from "react";
import axios from 'axios';
const AddCard = () => {
    const {deckId}=useParams()
    let navigate= useNavigate()

    const [card,setCard]= useState({
        frontText:"",
        backText:""
    })
    

    const{frontText, backText}=card

    const onInputChange=(e)=>{
        setCard({...card,[e.target.name]: e.target.value})
    }

    const onAdd=async(e)=>{
        e.preventDefault();
        await axios.post("http://localhost:8080/card/add", {
        ...card,
        deck: { id: parseInt(deckId) }})
        navigate(`/${deckId}`)
    }

    return (
        <div className='container'>
            <div className='row'>
                <div className='col-md-6 offset-md-3 border rounded p-4 mt-2 shadow'>
                    <h2 className='text-center m-4'>Add Card</h2>
                    <form onSubmit={(e)=>onAdd(e)}>
                    <div className='mb-3'>
                        <label htmlFor='frontText' className='form-label'> Card Front Text:</label>
                        <input type={'text'} className='form-control' placeholder='Enter front text for your card' name='frontText' value={frontText} onChange={(e)=>onInputChange(e)}/>
                        <label htmlFor='backText' className='form-label'> Card Back Text:</label>
                        <input type={'text'} className='form-control' placeholder='Enter back text for your card' name='backText' value={backText} onChange={(e)=>onInputChange(e)}/>
                    </div>
                    <button type='submit' className='btn btn-light'>Add New Card</button>
                    <Link to={`/${deckId}`} className='btn btn-outline-danger mx-2'>cancel</Link>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default AddCard;
