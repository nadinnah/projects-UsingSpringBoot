import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import {useState} from "react";
import axios from 'axios';
const AddDeck = () => {

    let navigate= useNavigate()

    const [deck,setDeck]= useState({
        deckName:""
    })

    const{deckName}=deck
    const onInputChange=(e)=>{
        setDeck({...deck,[e.target.name]: e.target.value})
    }

    const onAdd=async(e)=>{
        e.preventDefault();
        await axios.post("http://localhost:8080/deck/add", deck)
        navigate("/")
    }

    return (
        <div className='container'>
            <div className='row'>
                <div className='col-md-6 offset-md-3 border rounded p-4 mt-2 shadow'>
                    <h2 className='text-center m-4'>Add Deck</h2>
                    <form onSubmit={(e)=>onAdd(e)}>
                    <div className='mb-3'>
                        <label htmlFor='Name' className='form-label'> Deck Name:</label>
                        <input type={'text'} className='form-control' placeholder='Enter deck name' name='deckName' value={deckName} onChange={(e)=>onInputChange(e)}/>
                    </div>
                    <button type='submit' className='btn btn-light'>Add</button>
                    <Link to={"/"} className='btn btn-outline-danger mx-2'>cancel</Link>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default AddDeck;
