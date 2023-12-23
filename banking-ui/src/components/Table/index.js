import React from 'react';

const Table = ({ columns, data }) => {
  return (
    <table className='table'>
      <thead>
        <tr>
          {columns.map(c => (
            <th key={c.title} scope='col'>
              {c.title}
            </th>
          ))}
        </tr>
      </thead>
      <tbody>
        {data
          ? data.map(u => (
              <tr key={u.id}>
                {columns.map(c => {
                  if (c.title === 'Actions') {
                    return <td key={c.title}>{c.field(u)}</td>;
                  } else {
                    return <td key={c.title}>{u[c.field]}</td>;
                  }
                })}
              </tr>
            ))
          : null}
      </tbody>
    </table>
  );
};

export default Table;
