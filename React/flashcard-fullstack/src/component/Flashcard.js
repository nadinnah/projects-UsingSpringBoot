import React, {useState} from 'react';

const Flashcard = ({flashcard}) => {
    const [flip, setFlip]= useState(false)

    return (
        <div className={`card ${flip? 'flip' :''}`} 
        onClick={()=>setFlip(!flip)}
        >
            <div className='front'>
                {flashcard.frontText}
            </div>
            
            <div className='back'>
                {flashcard.backText}
            </div>
        </div>
    );
}

export default Flashcard;
