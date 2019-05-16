package com.cellar.wine.services.impl;

import com.cellar.wine.models.Producer;
import com.cellar.wine.repositories.ProducerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ProducerServiceImplTest {

    private static final String PRODUCER = "Producer";

    @Mock
    ProducerRepository producerRepository;

    @InjectMocks
    ProducerServiceImpl producerServiceImpl;

    private Producer returnProducer;

    @BeforeEach
    void setUp() {
        returnProducer = Producer.builder().id(1L).name(PRODUCER).build();
    }

    @Test
    void findAll() {

        Set<Producer> producerSet = new HashSet<>();
        producerSet.add(Producer.builder().id(1L).build());
        producerSet.add(Producer.builder().id(2L).build());

        when(producerRepository.findAll()).thenReturn(producerSet);

        Set<Producer> producers = producerServiceImpl.findAll();
        assertNotNull(producers);
        assertEquals(2, producers.size());
    }

    @Test
    void findById() {
        when(producerRepository.findById(1L)).thenReturn(Optional.of(returnProducer));
        Producer producer = producerServiceImpl.findById(1L);
        assertNotNull(producer);
    }

    @Test
    void save() {
        Producer producer = Producer.builder().id(1L).build();
        when(producerRepository.save(producer)).thenReturn(returnProducer);
        Producer savedProducer = producerServiceImpl.save(producer);
        assertNotNull(savedProducer);
        verify(producerRepository).save(any());
    }

    @Test
    void delete() {
        producerServiceImpl.delete(returnProducer);
        verify(producerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        producerServiceImpl.deleteById(1L);
        verify(producerRepository).deleteById(anyLong());
    }
}