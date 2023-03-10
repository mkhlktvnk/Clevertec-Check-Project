package com.clevertec.clevertectesttaskrest.aspect;

import com.clevertec.clevertectesttaskrest.cache.Cache;
import com.clevertec.clevertectesttaskrest.domain.Contact;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Aspect
@Component
@RequiredArgsConstructor
public class ContactServiceAspect {
    private final Cache<Long, Contact> contactCache;

    @Around(value = "getById()", argNames = "joinPoint")
    public Object findInCache(ProceedingJoinPoint joinPoint) throws Throwable {
        Long id = (Long) joinPoint.getArgs()[0];
        Optional<Contact> contact = contactCache.get(id);
        if (contact.isPresent()) {
            return contact.get();
        }
        return joinPoint.proceed(joinPoint.getArgs());
    }

    @AfterReturning(value = "getById()", returning = "retVal")
    public void saveIfNotInCache(Object retVal) {
        Contact contact = (Contact) retVal;
        if (contactCache.get(contact.getId()).isEmpty()) {
            contactCache.put(contact.getId(), contact);
        }
    }

    @AfterReturning(value = "create()", returning = "retVal")
    public void saveInCache(Object retVal) {
        Contact returnedContact = (Contact) retVal;
        contactCache.put(returnedContact.getId(), returnedContact);
    }

    @After(value = "update()")
    public void updateInCache(JoinPoint joinPoint) {
        Contact contact = (Contact) joinPoint.getArgs()[1];
        contactCache.put(contact.getId(), contact);
    }

    @After(value = "deleteById()")
    public void deleteFromCache(JoinPoint joinPoint) {
        Long id = (Long) joinPoint.getArgs()[0];
        contactCache.remove(id);
    }

    @Pointcut("execution(* com.clevertec.clevertectesttaskrest.service.ContactService.getById(..))")
    private void getById() {

    }

    @Pointcut("execution(* com.clevertec.clevertectesttaskrest.service.ContactService.create(..))")
    private void create() {

    }

    @Pointcut("execution(* com.clevertec.clevertectesttaskrest.service.ContactService.update(..))")
    private void update() {

    }

    @Pointcut("execution(* com.clevertec.clevertectesttaskrest.service.ContactService.deleteById(..))")
    private void deleteById() {

    }
}
