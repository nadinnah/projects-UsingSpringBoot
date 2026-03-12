import React from 'react';
import Deck from './Deck';
import { Link } from 'react-router-dom';

const DeckList = ({decks}) => {
    

    return (
       <div >
            {decks.map(deck=>{
                 
                return <Link to={`/${deck.id}`}><Deck deck={deck} key={deck.id}/></Link>
            })}
        </div>
    );
}

export default DeckList;
