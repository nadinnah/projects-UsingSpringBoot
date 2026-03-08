import React from 'react';
import Deck from './Deck';
import { Link,useParams } from 'react-router-dom';

const DeckList = ({decks}) => {
    const{id}=useParams()

    return (
       <div >
            {decks.map(deck=>{
                 
                return <Link to={`/cardpage/:${deck.id}`}><Deck deck={deck} key={deck.id}/></Link>
            })}
        </div>
    );
}

export default DeckList;
